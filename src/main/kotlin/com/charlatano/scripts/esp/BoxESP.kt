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

import com.badlogic.gdx.graphics.Color
import com.charlatano.game.entity.*
import com.charlatano.game.entity.EntityType.Companion.ccsPlayer
import com.charlatano.game.entityByType
import com.charlatano.game.forEntities
import com.charlatano.game.me
import com.charlatano.game.worldToScreen
import com.charlatano.overlay.CharlatanoOverlay
import com.charlatano.settings.*
import com.charlatano.utils.Vector
import com.badlogic.gdx.Gdx.gl
import com.charlatano.settings.LINE_WIDTH

private val vHead = Vector()
private val vFeet = Vector()

private val vTop = Vector(0.0, 0.0, 0.0)
private val vBot = Vector(0.0, 0.0, 0.0)

private val boxes = Array(128) { Box() }
private val hps = Array(128) { HP() }

private var currentIdx = 0

internal fun boxEsp() = CharlatanoOverlay {
	if (!BOX_ESP || !ENABLE_ESP) return@CharlatanoOverlay

	val bomb: Entity = entityByType(EntityType.CC4)?.entity ?: -1

	forEntities(ccsPlayer) {
		val entity = it.entity
		if (entity == me || entity.dead() || entity.dormant()) return@forEntities

		vHead.set(entity.bone(0xC), entity.bone(0x1C), entity.bone(0x2C) + 9)
		vFeet.set(vHead.x, vHead.y, vHead.z - 75)

		if (worldToScreen(vHead, vTop) && worldToScreen(vFeet, vBot)) {
			val boxH = vBot.y - vTop.y
			val boxW = boxH / 5F

			val c = if (bomb > 0 && entity == bomb.carrier()) BOMB_CARRIER_COLOR
			else if (me.team() == entity.team()) TEAM_COLOR
			else ENEMY_COLOR

			val drawColor = Color(c.red / 255f, c.green / 255f, c.blue / 255f, 1f)

			val sx = (vTop.x - boxW).toInt()
			val sy = vTop.y.toInt()

			boxes[currentIdx].apply {
				x = sx
				y = sy
				w = Math.ceil(boxW * 2).toInt()
				h = boxH.toInt()
				color = drawColor
			}
			val w = entity.weapon();
			hps[currentIdx].apply {
				x = sx + 80f
				y = sy - 30f
				hp = entity.health()
				weapon = "$w"
				color = drawColor
			}

			currentIdx++
		}
	}

	shapeRenderer.apply sR@{
		begin()
		gl.glLineWidth(LINE_WIDTH);
		for (i in 0..currentIdx - 1) boxes[i].apply {
			this@sR.color = color
			rect(x.toFloat(), y.toFloat(), w.toFloat(), h.toFloat())
		}
		if (SHOW_WEAPON_AND_HEALTH) {
			batch.begin()
			for (i in 0..currentIdx - 1) hps[i].apply {
				textRenderer.color = color
				textRenderer.draw(batch, "$weapon $hp%", x, y)
			}
			batch.end()
		}
		end()
	}

	currentIdx = 0
}

private data class HP(var x: Float = -1f, var y: Float = -1f,
					  var hp: Int = -1, var color: Color = Color.WHITE, var weapon: String = "")

private data class Box(var x: Int = -1, var y: Int = -1,
					   var w: Int = -1, var h: Int = -1,
					   var color: Color = Color.WHITE)
			