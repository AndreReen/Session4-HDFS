import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Airline {

  @CsvBindByPosition(position = 0)
  private int ID;
  @CsvBindByPosition(position = 1)
  private String Name;
  @CsvBindByPosition(position = 2)
  private String Alias;
  @CsvBindByPosition(position = 3)
  private String IATA;
  @CsvBindByPosition(position = 4)
  private String ICAO;
  @CsvBindByPosition(position = 5)
  private String Callsign;
  @CsvBindByPosition(position = 6)
  private String Country;
  @CsvBindByPosition(position = 7)
  private String Active;

}
