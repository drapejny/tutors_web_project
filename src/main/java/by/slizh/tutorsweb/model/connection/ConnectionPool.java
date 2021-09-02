package by.slizh.tutorsweb.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();

    private static ConnectionPool instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final ReentrantLock locker = new ReentrantLock();
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> givenAwayConnections;
    private static final String PATH_TO_PROPERTIES = "database/database.properties";
    private static final int DEFAULT_POOL_SIZE = 32;

    private ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            ProxyConnection proxyConnection = null;
            try {
                proxyConnection = (ProxyConnection) ConnectionFactory.createConnection();
                freeConnections.put(proxyConnection);
            } catch (SQLException e) {
                logger.fatal("Can't create connection for connection pool", e);
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                logger.fatal("Something wrong with current thread",e);
                throw new RuntimeException(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            givenAwayConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("Can't put connection to givenAwayConnections",e);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            givenAwayConnections.remove(connection);
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Can't put connection to freeConnections",e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.fatal("Wrong connection is detected, expected ProxyConnection object");
            throw new RuntimeException();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException e) {
                logger.error("Close connection when destroying pool", e);
            } catch (InterruptedException e) {
                logger.error("Interrapted while waiting: ", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Exception when deregistering driver ", driver, e);
            }
        });
    }


}
