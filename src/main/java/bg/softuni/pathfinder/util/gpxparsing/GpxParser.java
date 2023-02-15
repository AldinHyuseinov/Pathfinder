package bg.softuni.pathfinder.util.gpxparsing;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.NoArgsConstructor;

import java.io.StringReader;
import java.util.List;

@NoArgsConstructor
public class GpxParser {

    public List<TrackPoint> parse(String gpxString) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Track.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Track track = (Track) jaxbUnmarshaller.unmarshal(new StringReader(trackSegmentString(gpxString)));

            return track.getTrackPoints();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String trackSegmentString(String gpx) {
        String[] parts = gpx.split("<trkseg>");
        StringBuilder sb = new StringBuilder();

        for (String part : parts) {
            int endIndex = part.indexOf("</trkseg>");

            if (endIndex != -1) {
                sb.append("<trkseg>").append(part, 0, endIndex).append("</trkseg>");
            }
        }
        return sb.toString();
    }
}
