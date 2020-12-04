import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PolygonTest {

    Polygon defaultPolygon = new Polygon();
    Polygon defaultPolygon2 = new Polygon();

    @BeforeEach
    void setUp() {
        defaultPolygon.addVertex(2, 1);
        defaultPolygon.addVertex(5, 0);
        defaultPolygon.addVertex(6, 2);
        defaultPolygon.addVertex(6.5, 3);
        defaultPolygon.addVertex(7, 5);
        defaultPolygon.addVertex(6, 6);
        defaultPolygon.addVertex(4, 6);
        defaultPolygon.addVertex(2, 5.5);
        defaultPolygon.addVertex(1, 4);
        defaultPolygon.addVertex(1, 2);

        defaultPolygon2.addVertex(2,1);
        defaultPolygon2.addVertex(3,1);
        defaultPolygon2.addVertex(4,3);
        defaultPolygon2.addVertex(3,4);
        defaultPolygon2.addVertex(1,3);
    }

    @Test
    void addVertex() {
        assertEquals(false, defaultPolygon.addVertex(1,5));

        Polygon polygon1 = new Polygon();
        assertEquals(true, polygon1.addVertex(2, 1));
        polygon1.addVertex(5, 0);
        polygon1.addVertex(6, 2);
        assertEquals(true, polygon1.addVertex(6.5, 3));
        polygon1.addVertex(7, 5);
        polygon1.addVertex(6, 6);
        polygon1.addVertex(4, 6);
        polygon1.addVertex(2, 5.5);
        polygon1.addVertex(1, 4);
        assertEquals(true, polygon1.addVertex(1, 2));
        assertEquals(false, polygon1.addVertex(1, 2));
        assertEquals(false, polygon1.addVertex(1, 2));
        assertEquals(false, polygon1.addVertex(1, 2));
    }

    @Test
    void highestVertex() {
        Point highestDefPoint = new Point(6, 6);
        Point highestDefPoint2 = new Point(4, 6);
        Point highestPoint1 = defaultPolygon.highestVertex();
        assertEquals(true, highestDefPoint.equals(highestPoint1));
        assertEquals(false, highestPoint1.equals(highestDefPoint2));

        highestDefPoint2.move(2,0);
        assertEquals(true, highestPoint1.equals(highestDefPoint2));

        highestDefPoint2.move(2,0);
        assertEquals(false, highestPoint1.equals(highestDefPoint2));

        Polygon polygon1 = new Polygon();
        assertEquals(null, polygon1.highestVertex());

        Point point1 = new Point(6, 2);
        polygon1.addVertex(6, 2);

        assertEquals(true, polygon1.highestVertex().equals(point1));

        Point point2 = new Point(6.5, 3);
        assertEquals(true, polygon1.addVertex(6.5, 3));
        assertEquals(false, polygon1.highestVertex().equals(point1));
        assertEquals(true, polygon1.highestVertex().equals(point2));

        assertEquals(true, polygon1.addVertex(2, 1));
        assertEquals(true, polygon1.highestVertex().equals(point2));

        assertEquals(true, polygon1.addVertex(2, 10));
        assertEquals(false, polygon1.highestVertex().equals(point2));
    }

    @Test
    void testToString() {
        String desiredStr = "The polygon has 5 vertices:\n" +
                "((2.0,1.0),(5.0,0.0),(7.0,5.0),(4.0,6.0),(1.0,4.0))";
        Polygon polygon = new Polygon();
        polygon.addVertex(2, 1);
        polygon.addVertex(5, 0);
        polygon.addVertex(7, 5);
        polygon.addVertex(4, 6);
        polygon.addVertex(1, 4);


        assertEquals(true, desiredStr.equals(polygon.toString()));

        polygon.addVertex(2, 5.5);
        assertEquals(false, desiredStr.equals(polygon.toString()));

        Polygon polygon2 = new Polygon();

        String noVerticesStr = "The polygon has 0 vertices.";
        assertEquals(false, noVerticesStr.equals(polygon.toString()));
        assertEquals(true, noVerticesStr.equals(polygon2.toString()));


        polygon2.addVertex(2, 1);
        polygon2.addVertex(5, 0);
        polygon2.addVertex(6, 2);
        polygon2.addVertex(6.5, 3);
        polygon2.addVertex(7, 5);
        polygon2.addVertex(6, 6);
        polygon2.addVertex(4, 6);
        polygon2.addVertex(2, 5.5);
        polygon2.addVertex(1, 4);
        polygon2.addVertex(1, 2);

        assertEquals(true, polygon2.toString().equals(defaultPolygon.toString()));
        assertEquals(false, desiredStr.equals(polygon2.toString()));

        Polygon polygon3 = new Polygon();
        polygon3.addVertex(2, 1);
        polygon3.addVertex(5, 0);
        polygon3.addVertex(6, 2);
        polygon3.addVertex(6.5, 3);
        polygon3.addVertex(7, 5);
        polygon3.addVertex(6, 6);
        polygon3.addVertex(4, 6);
        polygon3.addVertex(2, 5.5);
        polygon3.addVertex(1, 4);
        polygon3.addVertex(1, 2);

        assertEquals(true, polygon2.toString().equals(polygon3.toString()));

        polygon2.addVertex(1,100);

        assertEquals(true, polygon3.toString().equals(polygon2.toString()));

        Polygon polygon4 = new Polygon();
        polygon4.addVertex(2, 1);
        polygon4.addVertex(5, 0);
        polygon4.addVertex(6, 2);
        polygon4.addVertex(6.5, 3);
        polygon4.addVertex(7, 5);
        polygon4.addVertex(1, 4);
        polygon4.addVertex(6, 6);
        polygon4.addVertex(4, 6);
        polygon4.addVertex(2, 5.5);
        polygon4.addVertex(1, 2);

        assertEquals(false, polygon3.toString().equals(polygon4.toString()));

        Polygon polygon5 = new Polygon();
        polygon5.addVertex(2, 1);
        polygon5.addVertex(5, 0);
        polygon5.addVertex(6, 2);
        polygon5.addVertex(6.5, 3);
        polygon5.addVertex(7, 5);
        polygon5.addVertex(1, 4);
        polygon5.addVertex(6, 6);
        polygon5.addVertex(4, 6);
        polygon5.addVertex(2, 5.5);

        assertEquals(false, polygon4.toString().equals(polygon5.toString()));

        polygon5.addVertex(1,2);
        assertEquals(true, polygon5.toString().equals(polygon4.toString()));
    }

    @Test
    void calcPerimeter() {

        double periComplex = 1 + Math.sqrt(2) + (Math.sqrt(5) * 3);
        double periSimple = 9.1224;

        assertEquals(periComplex, defaultPolygon2.calcPerimeter(), 0.01);
        assertEquals(periSimple, defaultPolygon2.calcPerimeter(), 0.01);

        assertEquals(false, defaultPolygon2.calcPerimeter() - defaultPolygon.calcPerimeter() == 0);

        Polygon polygon2 = new Polygon();
        polygon2.addVertex(2,1);
        polygon2.addVertex(3,1);
        polygon2.addVertex(4,3);
        polygon2.addVertex(3,4);
        polygon2.addVertex(1,3);

        assertEquals(true, defaultPolygon2.calcPerimeter() - polygon2.calcPerimeter() == 0);
        assertEquals(0, defaultPolygon2.calcPerimeter() - polygon2.calcPerimeter(), 0.01);

        polygon2.addVertex(1, 2);

        assertEquals(false, defaultPolygon2.calcPerimeter() - polygon2.calcPerimeter() == 0);


        Polygon polygon3 = new Polygon();
        assertEquals(0, polygon3.calcPerimeter());

        polygon3.addVertex(2,1);
        assertEquals(0, polygon3.calcPerimeter());

        polygon3.addVertex(3,1);
        assertEquals(1, polygon3.calcPerimeter());

        polygon3.addVertex(4,3);
        assertEquals(1 + Math.sqrt(5) + Math.sqrt(8), polygon3.calcPerimeter());

    }

/*    @Test
    void triangleArea() {
        Point a = new Point(3,1);
        Point b = new Point(4,3);
        Point c = new Point(3,4);
        Polygon polygon = new Polygon();

        assertEquals(1.4996, polygon.triangleArea(a,b,c), 0.01);

        Point d = new Point(1,3);

        assertEquals(2.999, polygon.triangleArea(a,c,d), 0.01);

        Point e = new Point(2,1);

        assertEquals(1, polygon.triangleArea(a,d,e), 0.01);

        polygon.addVertex(a.getX(), a.getY());
        polygon.addVertex(b.getX(), b.getY());
        polygon.addVertex(c.getX(), c.getY());
        polygon.addVertex(d.getX(), d.getY());
        polygon.addVertex(e.getX(), e.getY());

        double expectedArea = polygon.triangleArea(a, b, c) + polygon.triangleArea(a,c,d) + polygon.triangleArea(a,d,e);

        assertEquals(expectedArea, polygon.calcArea(), 0.01);
    }*/

    @Test
    void calcArea() {
        // Using the website https://www.mathopenref.com/coordpolygonareacalc.html

        assertEquals(26.5, defaultPolygon.calcArea(), 0.01);

        Polygon polygon = new Polygon();
        polygon.addVertex(2,1);
        polygon.addVertex(5,0);
        polygon.addVertex(6,2);
        polygon.addVertex(6.5,3);
        polygon.addVertex(7,5);
        polygon.addVertex(6,6);
        polygon.addVertex(4,6);

        assertEquals(18.75, polygon.calcArea(), 0.01);

        Polygon polygon1 = new Polygon();
        assertEquals(0, polygon1.calcArea(), 0.01);

        polygon1.addVertex(2,1);
        assertEquals(0, polygon1.calcArea(), 0.01);

        polygon1.addVertex(5,0);
        assertEquals(0, polygon1.calcArea(), 0.01);

        polygon1.addVertex(6,2);
        assertEquals(3.5, polygon1.calcArea(), 0.01);

        polygon1.addVertex(6.5,4);
        assertEquals(7.25, polygon1.calcArea(), 0.01);

    }
}