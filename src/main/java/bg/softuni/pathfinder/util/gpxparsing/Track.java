package bg.softuni.pathfinder.util.gpxparsing;

import java.util.List;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement(name = "trkseg")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Getter
@Setter
public class Track {
    @XmlElement(name = "trkpt")
    private List<TrackPoint> trackPoints;
}

