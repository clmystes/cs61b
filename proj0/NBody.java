class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int n = in.readInt();
        in.readDouble();

        Planet[] planets = new Planet[n];
        for (int i = 0; i < n; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet p : planets) {
            p.draw();
        }

//        enableDoubleBuffering();

        int time = 0;
        while (time != T) {
            int len = planets.length;
            double[] xForces = new double[len];
            double[] yForces = new double[len];

            for (int i = 0; i < len; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < len; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}