package com.github.vkennke.patchca.filter.predefined;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.vkennke.patchca.filter.FilterFactory;

public class RandomFilterFactory implements FilterFactory {
    private List<FilterFactory> filterFactories = new ArrayList<>();
    private Random random = new Random();

    public void addFilterFactory(FilterFactory filterFactory) {
        filterFactories.add(filterFactory);
    }

    @Override
    public BufferedImage applyFilters(BufferedImage source) {
        FilterFactory filterFactory = filterFactories.get(random.nextInt(filterFactories.size()));
        return filterFactory.applyFilters(source);
    }
}
