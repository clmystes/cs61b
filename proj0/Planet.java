import java.lang.Math;

class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67 * Math.pow(10, -11);

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return (G * this.mass * p.mass) / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return (f * dx) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return (f * dy) / r;
    }
}