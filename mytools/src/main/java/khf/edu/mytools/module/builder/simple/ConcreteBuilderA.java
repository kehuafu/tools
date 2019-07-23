package khf.edu.mytools.module.builder.simple;


public class ConcreteBuilderA implements Builder {
    private Product product;
    @Override
    public void buildPartOne() {
        System.out.println("--------");
    }

    @Override
    public void buildPartOTwo() {
        System.out.println("+++++++++");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
