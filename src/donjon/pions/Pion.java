package donjon.pions;

public class Pion {
    private int m_x;
    private int m_y;
    private String m_symbol;

    public Pion(int x, int y, String symbol){
        m_x = x;
        m_y = y;
        m_symbol = symbol;
    }

    public int getX(){
        return m_x;
    }

    public int getY() {
        return m_y;
    }

    public void setPosition(int x, int y){
        m_x = x;
        m_y = y;
    }

    @Override
    public String toString() {
        return m_symbol;
    }
}
