package expression;

import java.util.HashMap;
import java.util.Objects;

public class Conjunction implements Expression {

    private Expression left; //Conjunction
    private Expression right; //Negation
    private Integer hash = null;
    public Integer hashCodeX() {
        if(hash != null) {
            return hash;
        }
        hash = Objects.hash("&",
                left.hashCodeX().toString(), right.hashCodeX().toString());
        return hash;
    }
    public Conjunction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toTree() {
        return "(&," + left.toTree() + "," + right.toTree() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conjunction that = (Conjunction) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public boolean recursivlyCompare(Expression expr, HashMap<String, Integer> varM) {
        if(!expr.getType().equals(this.getType())) {
            return false;
        } else {
            return left.recursivlyCompare(expr.getLeft(),varM) && right.recursivlyCompare(expr.getRight(), varM);
        }
    }

    @Override
    public String getType() {
        return "&";
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "&" + right.toString() + ")";
    }
}