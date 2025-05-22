package com.bluepal.service;



import java.util.List;

import com.bluepal.model.Home;
import com.bluepal.model.HomeCategory;

public interface HomeService {

    Home creatHomePageData(List<HomeCategory> categories);

}
