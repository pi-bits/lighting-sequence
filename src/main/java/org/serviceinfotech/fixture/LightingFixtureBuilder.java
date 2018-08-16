package org.serviceinfotech.fixture;

import org.serviceinfotech.model.Colour;
import org.serviceinfotech.model.LightBulb;

import java.util.ArrayList;

public class LightingFixtureBuilder {
    private Integer fixtureSize;
    private Colour[] colors;

    public LightingFixtureBuilder(Integer fixtureSize, Colour... colors) {
        this.fixtureSize = fixtureSize;
        this.colors = colors;
    }


    public LightingFixture build() {
        ArrayList<LightBulb> lightBulbs = new ArrayList<>(this.fixtureSize);

        for (int i = 0; i < this.fixtureSize; i++) {
            for (int j = 0; j < colors.length && lightBulbs.size() < fixtureSize; j++) {
                lightBulbs.add(new LightBulb(colors[j]));
            }

        }
        return new LightingFixture(lightBulbs);
    }

}
