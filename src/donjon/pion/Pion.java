package donjon.pion;

public class Pion {
    private int m_x;
    private int m_y;
    private final String m_symbol;

    public Pion(int x, int y, String symbol){
        m_x = x;
        m_y = y;
        m_symbol = symbol;
    }

    public Pion(Pion p){
        m_x = p.m_x;
        m_y = p.m_y;
        m_symbol = p.m_symbol;
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

    public void setPosition(Pion p){
        setPosition(p.getX(), p.getY());
    }

    public int getDistance(int x, int y){
        return Math.max(Math.abs(m_x - x),Math.abs(m_y - y));
    }

    public boolean equals(Pion p) {
        return (m_x == p.m_x && m_y == p.m_y);
    }

    @Override
    public String toString() {
        return m_symbol;
    }
}
