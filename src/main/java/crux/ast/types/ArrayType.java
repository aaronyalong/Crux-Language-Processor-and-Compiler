package crux.ast.types;

/**
 * The variable base is the type of the array element. This could be int or bool. The extent
 * variable is number of elements in the array.
 *
 */
public final class ArrayType extends Type implements java.io.Serializable {
  static final long serialVersionUID = 12022L;
  private final Type base;
  private final long extent;

  public ArrayType(long extent, Type base) {
    this.extent = extent;
    this.base = base;
  }

  public Type getBase() {
    return base;
  }

  public long getExtent() {
    return extent;
  }

  @Override
  public String toString() {
    return String.format("array[%d,%s]", extent, base);
  }

  @Override
  public Type index(Type that) {
    if(this.equivalent(that)) {
      return base;
    } else {
      return super.index(that);}
  }

  @Override
  public Type assign(Type that) {
    if (that instanceof ArrayType && this.base.equivalent(((ArrayType) that).base)) {
      return new VoidType();
    } else {
      return super.assign(that);
    }
  }

  @Override
  public boolean equivalent(Type that) {
    if (that instanceof ArrayType) {
      ArrayType other = (ArrayType) that;
      return this.base.equivalent(other.base) && this.extent == other.extent;
    }
    return false;
  }
}
