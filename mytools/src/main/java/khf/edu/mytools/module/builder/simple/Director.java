package khf.edu.mytools.module.builder.simple;

public class Director {
    private Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }
    public void buildProduct(){
        this.builder.buildPartOne();
        this.builder.buildPartOTwo();
    }
    public Product getProduct(){
        return this.builder.getProduct();
    }

    public static void main(String[] args){
        //建造器
        Builder builder = new ConcreteBuilderA();
        //Director
        Director director = new Director(builder);
        director.buildProduct();

    }
}
