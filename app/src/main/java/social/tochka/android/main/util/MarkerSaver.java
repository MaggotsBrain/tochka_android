package social.tochka.android.main.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.experimental.UtilityClass;
import social.tochka.android.main.cards.TochkaCard;

@UtilityClass
public class MarkerSaver {

    public final Map<UUID, TochkaCard> tochkaMap = new HashMap<>();

}
