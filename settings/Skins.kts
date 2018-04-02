/*
 * Charlatano: Free and open-source (FOSS) cheat for CS:GO/CS:CO
 * Copyright (C) 2017 - Thomas G. P. Nappo, Jonathan Beaudoin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.charlatano.settings.*

import java.util.*
import com.charlatano.game.Weapons
import com.charlatano.game.Skin

/**
 * Syntax :
 *  Weapons.WeaponName to Skin(SkinID, Quality, StatTrak, Seed, Wear, name)
 *
 * You can find weapon names from src/main/kotlin/com/charlatano/game/Weapon.kt
 * You can find skin ids from src/main/kotlin/com/charlatano/game/Skin.kt
 *
 * Skin() without any params will try to load a random skin at every skin reload from console.
 */
SKINS = hashMapOf (
    Weapons.DESERT_EAGLE to Skin(351, 7, 132),
    Weapons.DUAL_BERRETA to Skin(),
    Weapons.FIVE_SEVEN to Skin(427, 0),
    Weapons.GLOCK to Skin(38, 1, 231),
    Weapons.AK47 to Skin(302, 6, 999999),
    Weapons.AUG to Skin(455,0),
    Weapons.AWP to Skin(344,12, 999999),
    Weapons.FAMAS to Skin(154, 0),
    Weapons.G3SG1 to Skin(511, 0),
    Weapons.GALIL to Skin(379, 12),
    Weapons.M249 to Skin(496, 0),
    Weapons.M4A4 to Skin(309, 12, 999999, 1, 0.000001f, "OH MY 天"),
    Weapons.MAC10 to Skin(482, 9),
    Weapons.P90 to Skin(482, 0),
    Weapons.UMP45 to Skin(556, 1, 231),
    Weapons.XM1014 to Skin(393, 0),
    Weapons.PP_BIZON to Skin(542, 0),
    Weapons.MAG7 to Skin(431, 0),
    Weapons.NEGEV to Skin(317, 0),
    Weapons.SAWED_OFF to Skin(256, 0),
    Weapons.TEC9 to Skin(287, 0), 
    Weapons.P2000 to Skin(482, 0),
    Weapons.MP7 to Skin(482, 0),
    Weapons.MP9 to Skin(482, 0),
    Weapons.NOVA to Skin(286, 0),
    Weapons.P250 to Skin(482, 0),
    Weapons.SCAR20 to Skin(391, 0),
    Weapons.SG556 to Skin(287, 0),
    Weapons.SSG08 to Skin(624, 0), 
    Weapons.M4A1_SILENCER to Skin(257, 1, 999999),
    Weapons.USP_SILENCER to Skin(277, 1, 21),
    Weapons.CZ75A to Skin(12, 12, 123),
    Weapons.R8_REVOLVER to Skin(12, 0)
)