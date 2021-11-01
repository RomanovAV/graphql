package com.example.demo.datafetchers;

import com.example.demo.generated.types.Compliance;
import com.example.demo.generated.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;


import java.util.*;

@DgsComponent
public class ReviewsDatafetcher {

    Map<String, List<Compliance>> compliance = new HashMap<>();

    public ReviewsDatafetcher() {
        List<Compliance> review1 = new ArrayList<>();
        review1.add(new Compliance(true, null));
        compliance.put("1", review1);

        List<Compliance> review2 = new ArrayList<>();
        review2.add(new Compliance(false, "18+ Forbidden"));
        compliance.put("2", review2);
    }

    @DgsEntityFetcher(name = "Show")
    public Show movie(Map<String, Object> values) {
        return new Show((String) values.get("id"), null);
    }

    @DgsData(parentType = "Show", field = "compliance")
    public List<Compliance> complianceFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        Show show = dataFetchingEnvironment.getSource();
        return compliance.get(show.getId());
    }
}
