package com.danimo.restaurant.category.application.usecases.listcategory;

import com.danimo.restaurant.category.application.inputports.ListingAllCategoriesInputPort;
import com.danimo.restaurant.category.application.outputports.persistence.FindingAllCategoriesOutPort;
import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.application.annotations.UseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class ListAllCateogries implements ListingAllCategoriesInputPort {
    private final FindingAllCategoriesOutPort findingAllCategoriesOutPort;

    @Autowired
    public ListAllCateogries(FindingAllCategoriesOutPort findingAllCategoriesOutPort) {
        this.findingAllCategoriesOutPort = findingAllCategoriesOutPort;
    }

    @Override
    public List<Category> getAllCategories() {
        return findingAllCategoriesOutPort.findAllCategories();
    }
}
