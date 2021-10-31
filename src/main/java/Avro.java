import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

public class Avro {

  public static void writeListToAvroFile(List<Airline> bean, String path, String fileName)
      throws IOException {
    Schema schema = ReflectData.get().getSchema(Airline.class);

    File file = new File(path + "/" + fileName);

    DatumWriter<Airline> writer = new ReflectDatumWriter<>(Airline.class);
    DataFileWriter<Airline> out = new DataFileWriter<>(writer)
        .setCodec(CodecFactory.deflateCodec(9))
        .create(schema, file);

    for (Airline airline : bean) {
      out.append(airline);
    }
    out.close();
  }


  public static void readAvroFile(String path, String fileName) throws IOException {

    File file = new File(path + fileName);
    DatumReader<Airline> reader = new ReflectDatumReader<>(Airline.class);
    DataFileReader<Airline> in = new DataFileReader<>(file, reader);

    for (Airline airline : in) {
      System.out.println(ReflectData.get().toString(airline));
    }

    in.close();
  }

}