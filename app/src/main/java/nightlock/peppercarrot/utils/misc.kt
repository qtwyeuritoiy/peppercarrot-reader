/*
 * Copyright (C) 2017 Jihoon Kim <imsesaok@gmail.com, imsesaok@tuta.io>
 *
 * This file is part of Reader for Pepper&Carrot.
 *
 * Reader for Pepper&Carrot is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nightlock.peppercarrot.utils

/**
 * Miscellaneous utility functions.
 * Created by Jihoon Kim on 21/07/17.
 */

fun getCoverImageUrl(episode: Episode) = "https://www.peppercarrot.com/0_sources/" +
        "${episode.name}/low-res/${getPreferredLanguage(episode)}_Pepper-and-Carrot_by-David-Revoy_" +
        "E${if (episode.index < 9) "0" else ""}${episode.index + 1}.jpg"

fun getComicImageUrl(episode: Episode, index: Int) = "https://www.peppercarrot.com/0_sources/" +
        "${episode.name}/low-res/${getPreferredLanguage(episode)}_Pepper-and-Carrot_by-David-Revoy_" +
        "E${if (episode.index < 9) "0" else ""}${episode.index + 1}P${if (index < 10) "0" else ""}$index.jpg"
