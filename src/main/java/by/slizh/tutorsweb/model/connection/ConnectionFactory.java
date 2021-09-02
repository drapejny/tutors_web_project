package by.slizh.tutorsweb.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {

    private static final Logger logger = LogManager.getLogger();

    private static final Properties properties = new Properties();
    private static final String PATH_TO_PROPERTIES = "database/database.properties";
    private static final String DATABASE_URL;
    private static final String DATABASE_URL_ATTRIBUTE_NAME = "db.url";
    private static final String DATABASE_DRIVER_ATTRIBUTE_NAME = "db.driver";

    static {
        String driverName = null;
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES)) {
            properties.load(inputStream);
            driverName = (String) properties.get(DATABASE_DRIVER_ATTRIBUTE_NAME);
            Class.forName(driverName);
            DATABASE_URL = (String) properties.get(DATABASE_URL_ATTRIBUTE_NAME);
            if (DATABASE_URL == null) {
                logger.fatal("Missing database url tag in propeties, file=" + PATH_TO_PROPERTIES);
                throw new ExceptionInInitializerError("Missing database url tag in propeties, file=" + PATH_TO_PROPERTIES);
            }
        } catch (ClassNotFoundException e) {
            logger.fatal("Register driver fatal error: ", e);
            throw new ExceptionInInitializerError("Register driver fatal error, driverName=" + driverName);
        } catch (IOException e) {
            logger.fatal("Properties file can't be read", e);
            throw new ExceptionInInitializerError("Properties file can't be read, file=" + PATH_TO_PROPERTIES);
        }

    }

    private ConnectionFactory() {
    }

    static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, properties);
        ProxyConnection proxyConnection = new ProxyConnection(connection);
        return proxyConnection;
    }
}

