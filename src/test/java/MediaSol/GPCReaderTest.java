package MediaSol;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import org.apache.tomcat.util.http.fileupload.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.mock.web.*;
import org.springframework.web.multipart.*;
import com.sun.jndi.toolkit.url.*;
import MediaSol.model.*;
import MediaSol.services.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GPCReaderTest {

    private GPCConverter gpcconverter = new GPCConverter();

    @Test
    public void shouldMatchAttributes() throws Exception {
        ClassPathResource resource = new ClassPathResource("Pohyby_na_uctu-1.gpc", getClass());;
        String radek4 = Files.readAllLines(Paths.get(resource.getURI()),StandardCharsets.ISO_8859_1).get(4) ;
        String Radek4_NazevPS=radek4.substring(98-1,98+20-1);
        Vypis vypis = gpcconverter.getVypis(resource.getFile());
        assertThat(Radek4_NazevPS).isEqualTo(vypis.getTransList().get(3).getNazevPS());
        assertThat("Ing. Yyyyyy, Xxxxx  ").isEqualTo(vypis.getMajitel());
        assertThat(159.6).isEqualTo(vypis.getTransList().get(0).getCastka());
    }

}
