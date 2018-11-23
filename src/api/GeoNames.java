package api;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

public class GeoNames {

    public static int cityNameToId(String city) throws Exception {
        return getCityName(city);
    }

    private static int getCityName(String city) throws Exception {
        int cityId = 0;
        WebService.setUserName("Oleg96");
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setQ(city);
        ToponymSearchResult searchResult = WebService.search(searchCriteria);
        for (Toponym toponym : searchResult.getToponyms()) {
            cityId = toponym.getGeoNameId();
            break;
//            System.out.println(toponym.getName()+" "+ toponym.getCountryName());
        }
        return cityId;
    }

}
