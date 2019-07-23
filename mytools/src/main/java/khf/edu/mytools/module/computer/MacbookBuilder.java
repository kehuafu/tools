package khf.edu.mytools.module.computer;

public class MacbookBuilder extends Builder {
    private Computer mComputer = new Macbook();
    @Override
    public void buildBoard(String board) {
        mComputer.setmBoard(board);
    }

    @Override
    public void buildDisplay(String display) {
        mComputer.setmDisplay(display);
    }

    @Override
    public void buildOS() {
        mComputer.setmOs();
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
