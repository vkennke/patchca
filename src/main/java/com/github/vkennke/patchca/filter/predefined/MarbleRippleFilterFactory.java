/*
 * Copyright (c) 2009 Piotr Piastucki
 *
 * This file is part of Patchca CAPTCHA library.
 *
 *  Patchca is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Patchca is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Patchca. If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.vkennke.patchca.filter.predefined;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

import com.github.vkennke.patchca.filter.library.MarbleImageOp;

public class MarbleRippleFilterFactory extends RippleFilterFactory {

    protected MarbleImageOp marble = new MarbleImageOp();

    @Override
    protected List<BufferedImageOp> getPreRippleFilters() {
        List<BufferedImageOp> list = new ArrayList<>();
        list.add(marble);
        return list;
    }

}

