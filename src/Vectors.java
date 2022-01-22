public class Vectors {
    //this class is planned to be for general vectors, but for now it is only three dimensional.
    private final double[] columnMatrix = new double[3];
    public Vectors(double x, double y, double z) {
        columnMatrix[0] = x;
        columnMatrix[1] = y;
        columnMatrix[2] = z;
    }
    public Vectors(double[] col) {
        columnMatrix[0] = col[0];
        columnMatrix[1] = col[1];
        columnMatrix[2] = col[2];
    }

    public void setX(double newPos) {
        columnMatrix[0] = newPos;
    }

    public void setY(double newPos) {
        columnMatrix[1] = newPos;
    }

    public void setZ(double newPos) {
        columnMatrix[2] = newPos;
    }

    public double[] getColumnMatrix() {
        return columnMatrix;
    }

    public double getPosX() {
        return columnMatrix[0];
    }

    public double getPosY() {
        return columnMatrix[1];
    }

    public double getPosZ() {
        return columnMatrix[2];
    }

    public double[] getPos(){
        return new double[] {this.getPosX(), this.getPosY(), this.getPosZ()};
    }

    public Matrix toColumnMatrix() throws Exception {
        double [][] matrix ={{this.getPosX()}, {this.getPosY()}, {this.getPosZ()}};
        return new Matrix(matrix);
    }
}
