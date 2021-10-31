import com.opencsv.bean.CsvToBeanBuilder;
import java.io.StringReader;
import java.util.List;

public class Csv {

  public static List<?> csvToList(Class<?> myClass, String csv) {

    List<?> beans;

    beans = new CsvToBeanBuilder<>(new StringReader(csv))
        .withType(myClass)
        .build()
        .parse();

    return beans;
  }

}
