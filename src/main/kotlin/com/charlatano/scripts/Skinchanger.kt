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

package com.charlatano.scripts

import com.charlatano.game.me
import com.charlatano.game.forEntities
import com.charlatano.game.CSGO.clientDLL
import com.charlatano.game.CSGO.engineDLL
import com.charlatano.game.CSGO.csgoEXE
import com.charlatano.game.entity.*
import com.charlatano.game.entity.EntityType.Companion.combatWeapon
import com.charlatano.utils.extensions.uint
import com.charlatano.game.offsets.EngineOffsets.dwClientState
import com.charlatano.game.offsets.ClientOffsets.dwLocalPlayer
import com.charlatano.game.netvars.NetVarOffsets.hMyWeapons
import com.charlatano.game.netvars.NetVarOffsets.iItemDefinitionIndex
import com.charlatano.game.netvars.NetVarOffsets.nFallbackPaintKit
import com.charlatano.game.netvars.NetVarOffsets.iEntityQuality
import com.charlatano.game.netvars.NetVarOffsets.nFallbackStatTrak
import com.charlatano.game.netvars.NetVarOffsets.flFallbackWear
import com.charlatano.game.offsets.ClientOffsets.dwEntityList
import com.charlatano.game.netvars.NetVarOffsets.iItemIDHigh
import com.charlatano.game.netvars.NetVarOffsets.iItemIDLow
import com.charlatano.game.netvars.NetVarOffsets.OriginalOwnerXuidHigh
import com.charlatano.game.netvars.NetVarOffsets.OriginalOwnerXuidLow
import com.charlatano.game.netvars.NetVarOffsets.nFallbackSeed
import com.charlatano.game.netvars.NetVarOffsets.hOwnerEntity
import com.charlatano.game.netvars.NetVarOffsets.iAccountID
import com.charlatano.game.netvars.NetVarOffsets.szCustomName
import com.charlatano.game.offsets.EngineOffsets
import org.jire.arrowhead.keyPressed
import com.charlatano.game.Weapons
import com.charlatano.utils.every
import com.charlatano.settings.*
import com.charlatano.game.offsets.ScaleFormOffsets
import com.charlatano.game.CSGO.scaleFormDLL
import kotlin.concurrent.thread


object SkinChanger {
	val engine = engineDLL.uint(dwClientState)

	fun skinwepindex() = thread {
		while (true) {
			if (ENABLE_SKIN_CHANGER && me > 0 && !me.dead() && me.onGround() && !scaleFormDLL.boolean(ScaleFormOffsets.bCursorEnabled)) {
				forEntities {
					if (it.type.weapon) {
						val weaponEntity = it.entity
						val weaponID = csgoEXE.int(weaponEntity + iItemDefinitionIndex)
						val weapon = Weapons[weaponID]
						//FIXME: hOwnerEntity is not working as it should
						val owner = clientDLL.uint(dwEntityList + ((csgoEXE.uint(weaponEntity + hOwnerEntity) and 0xFFF) - 1L) * 0x10)
						var skin = SKINS.get(weapon)
						//System.out.println("$weaponEntity, $weaponID, $weapon, $owner, $me")
						if (owner == me) {
							//System.out.println("its mine! $weapon, $weaponEntity")
							if (skin != null) {
								val myAccountId = csgoEXE.int(weaponEntity + OriginalOwnerXuidLow)
								applySkin(weaponEntity, myAccountId, skin.skinID, skin.skinSeed, skin.statTrak, skin.quality, skin.wear, skin.customName)
							}
						}
					}
				}
				if (keyPressed(APPLY_SKIN_KEY)) {
					do {
						Thread.sleep(25)
					} while (keyPressed(APPLY_SKIN_KEY))
					val enginePointer = engineDLL.uint(dwClientState)
					csgoEXE[enginePointer + 0x174] = -1
				}
			}
			if(ENABLE_SKIN_CHANGER) Thread.yield() else Thread.sleep(500)
		}
	}

}

private fun applySkin(weaponEntity: Long, me: Int, skinID: Int, skinSeed: Int, statTrak: Int, quality: Int, wear: Float, name: String) {
	csgoEXE[weaponEntity + iItemIDHigh] = 1
	csgoEXE[weaponEntity + iAccountID] = me
	csgoEXE[weaponEntity + nFallbackPaintKit] = skinID
	csgoEXE[weaponEntity + nFallbackSeed] = skinSeed
	csgoEXE[weaponEntity + nFallbackStatTrak] = statTrak
	csgoEXE[weaponEntity + iEntityQuality] = quality
	csgoEXE[weaponEntity + flFallbackWear] = wear
	if (!name.isBlank()) {
		val chars = name.toCharArray();
		val length = Math.min(32, chars.size) - 1;
		for (c in 0..length) {
			csgoEXE[weaponEntity + szCustomName + 0x1 * c] = chars[c]
		}
	}
}
