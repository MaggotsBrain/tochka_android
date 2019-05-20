package social.tochka.android.main.cards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TochkaCard {

    private String latitudeSymbol;
    private String latitudeDegree;
    private String latitudeMinutes;
    private String latitudeSeconds;

    private String longitudeSymbol;
    private String longitudeDegree;
    private String longitudeMinutes;
    private String longitudeSeconds;

    private String text;
}