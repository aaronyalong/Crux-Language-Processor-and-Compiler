package crux.ast.types;

/**
 * The field args is a TypeList with a type for each param. The type ret is the type of the function
 * return. The function return could be int, bool, or void. This class should implement the call
 * method.
 */
public final class FuncType extends Type implements java.io.Serializable {
  static final long serialVersionUID = 12022L;

  private TypeList args;
  private Type ret;

  public FuncType(TypeList args, Type returnType) {
    this.args = args;
    this.ret = returnType;
  }

  public Type getRet() {
    return ret;
  }

  public TypeList getArgs() {
    return args;
  }

  @Override
  public String toString() {
    return "func(" + args + "):" + ret;
  }

  @Override
  public Type call(Type that){
    if (that instanceof TypeList && args.equivalent((TypeList) that)) {
      return ret;
    } else {
      return super.call(that);
    }
  }

  @Override
  public boolean equivalent(Type that) {
    if (that instanceof FuncType) {
      FuncType other = (FuncType) that;
      return this.args.equivalent(other.args) && this.ret.equivalent(other.ret);
    }
    return false;
  }
}
