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

package com.charlatano.scripts.esp

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.Gdx.gl
import com.charlatano.game.*
import com.charlatano.game.entity.*
import com.charlatano.scripts.*
import com.charlatano.settings.*
import com.charlatano.utils.*
import com.charlatano.game.CSGO.clientDLL
import com.charlatano.game.CSGO.gameHeight
import com.charlatano.game.CSGO.gameWidth
import com.charlatano.overlay.CharlatanoOverlay
import com.charlatano.utils.extensions.uint
import com.badlogic.gdx.graphics.Color
import com.charlatano.game.offsets.ClientOffsets.dwLocalPlayer


fun esp() {
	glowEsp()
	boxEsp()
	skeletonEsp()
	crosshair();
}


internal fun crosshair() {
	CharlatanoOverlay {
		val weapon = me.weapon()
		if (weapon.sniper && !me.isScoped()) {
			shapeRenderer.apply {
				begin()
				gl.glLineWidth(LINE_WIDTH * 10);
				color = Color(CROSSHAIR_COLOR.red / 255f, CROSSHAIR_COLOR.green / 255f, CROSSHAIR_COLOR.blue / 255f, CROSSHAIR_COLOR.alpha.toFloat())
				line((gameWidth / 2 - 50).toFloat(), (gameHeight / 2).toFloat(), (gameWidth / 2 + 50).toFloat(), (gameHeight / 2).toFloat())
				line((gameWidth / 2).toFloat(), (gameHeight / 2 - 50).toFloat(), (gameWidth / 2).toFloat(), (gameHeight / 2 + 50).toFloat())
				end()
			}
		}
	}
}