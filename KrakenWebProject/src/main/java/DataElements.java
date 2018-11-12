public class DataElements {
    
    private String symbol;
    private String headline;
    private String text;
    private int date;
    
    
    public DataElements(String Symbol,String Headline,String Text,int Date)
    {
        this.symbol = Symbol;
        this.headline = Headline;
        this.text = Text;
        this.date = Date;
    }
    
    public String getSymbol()
    {
        return symbol;
    }
    
    public String getHeadline()
    {
        return headline;
    }
    
    public String getText()
    {
        return text;
    }
    
    public int getDate()
    {
        return date;
    }
}