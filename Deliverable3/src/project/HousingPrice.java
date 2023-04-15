package project;

public class HousingPrice {
	//variables
    private String refDate;
    private String geo;
    private String dguid;
    private String index;
    private String uom;
    private String uomId;
    private String scalarFactor;
    private String scalarId;
    private String vector;
    private String coordinate;
    
    private Double value;
    
    private String status;
    private String symbol;
    private String terminated;
    private int decimals;
    
    public HousingPrice(String refDate, String geo, String dguid, 
    		String index, String uom, String uomId, String scalarFactor, 
    		String scalarId, String vector, String coordinate, String valueStr, 
    		String status, String symbol, String terminated, int decimals) {
        //assign variables to correct locations
    	this.refDate = refDate;
        this.geo = geo;
        this.dguid = dguid;
        this.index = index;
        this.uom = uom;
        this.uomId = uomId;
        this.scalarFactor = scalarFactor;
        this.scalarId = scalarId;
        this.vector = vector;
        this.coordinate = coordinate;

        try { //in case of no value
            this.value = Double.parseDouble(valueStr);
        }
        catch (Exception e){
        	this.value = 0.0;
        }
               
        this.status = status;
        this.symbol = symbol;
        this.terminated = terminated;
        this.decimals = decimals;
    }

    //getters and setters
    public String getRefDate() {
        return refDate;
    }

    public void setRefDate(String refDate) {
        this.refDate = refDate;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getDguid() {
        return dguid;
    }

    public void setDguid(String dguid) {
        this.dguid = dguid;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUomId() {
        return uomId;
    }

    public void setUomId(String uomId) {
        this.uomId = uomId;
    }

    public String getScalarFactor() {
        return scalarFactor;
    }

    public void setScalarFactor(String scalarFactor) {
        this.scalarFactor = scalarFactor;
    }

    public String getScalarId() {
        return scalarId;
    }

    public void setScalarId(String scalarId) {
        this.scalarId = scalarId;
    }

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTerminated() {
        return terminated;
    }

    public void setTerminated(String terminated) {
        this.terminated = terminated;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }
}