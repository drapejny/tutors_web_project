package by.slizh.tutorsweb.command;

public class Router {

    public enum RouteType {
        FORWARD, REDIRECT
    }

    private String pagePath;
    private RouteType routeType = RouteType.FORWARD;

    public Router(String pagePath, RouteType routeType) {
        this.pagePath = pagePath;
        this.routeType = routeType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouteType getRouteType() {
        return routeType;
    }

}
