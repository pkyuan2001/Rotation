import java.util.Arrays;

public class Matrix {
    private final double [][] matrix;
    private final int rowLen;
    private final int colLen;
    public Matrix(double [][] matrix) throws Exception {
        rowLen = matrix[0].length;
        colLen = matrix.length;
     for(double [] row: matrix ){
         if(row.length != rowLen){
             throw new Exception("your rows must have same length");
         }
     }
     this.matrix = matrix;
    }
    public int getRowLen(){
        return this.rowLen;
    }
    public int getColLen(){
        return this.colLen;
    }
    public double[] getColumn(int columnNumber){
        double [] column = new double[colLen];
        int i = 0;
        for(double [] row: this.matrix){
            column[i] = row[columnNumber];
            i++;
        }
        return column;
    }
    private double dotProduct(double[] a, double[] b){
        double dotProduct = 0;
        for(int i = 0; i < a.length; i++){
            dotProduct += a[i] * b[i];
        }
        return dotProduct;
    }
    public Matrix mult(Matrix matrix) throws Exception {
        if (rowLen != matrix.getColLen()){
            throw new Exception("You cannot multiply these two matrices");
        }
        double [][] productMatrix = new double[this.getColLen()][matrix.getRowLen()];
        for(int row = 0; row < this.getColLen(); row++){
            for(int i  = 0; i < matrix.getRowLen(); i++) {
                double[] columnVector = matrix.getColumn(i);
                productMatrix[row][i] = dotProduct(this.matrix[row], columnVector);
            }
        }
        return new Matrix(productMatrix);
    }
    public String toString(){
        return Arrays.deepToString(this.matrix);
    }
}
