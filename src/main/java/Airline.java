import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Airline {

  @CsvBindByPosition(position = 0)
  private int id;
  @CsvBindByPosition(position = 1)
  private String name;
  @CsvBindByPosition(position = 2)
  private String alias;
  @CsvBindByPosition(position = 3)
  private String iata;
  @CsvBindByPosition(position = 4)
  private String icao;
  @CsvBindByPosition(position = 5)
  private String callsign;
  @CsvBindByPosition(position = 6)
  private String country;
  @CsvBindByPosition(position = 7)
  private String active;

}
