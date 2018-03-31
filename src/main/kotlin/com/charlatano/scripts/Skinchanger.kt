package com.charlatano.scripts

import com.charlatano.game.CSGO.clientDLL
import com.charlatano.game.CSGO.engineDLL
import com.charlatano.game.CSGO.csgoEXE
import com.charlatano.game.entity.Player
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
import com.charlatano.game.netvars.NetVarOffsets.nFallbackSeed
import com.charlatano.game.offsets.EngineOffsets
import com.charlatano.scripts.SkinChangerPlugin.weaponAddress
import com.charlatano.scripts.SkinChangerPlugin.weapon
import com.charlatano.settings.Skinchanger
import com.charlatano.game.Weapons
import com.charlatano.utils.every

object SkinChangerPlugin {
	val engine = engineDLL.uint(dwClientState)
	var weaponAddress = 0
	lateinit var weapon: Weapons

	private fun skins() {
	
		if (skin_AK47_STAT == 12) Weapons.AK47(skin_AK47, 12, 0, skin_AK47_WEAR)
		else if (skin_AK47_STAT > 0) Weapons.AK47(skin_AK47, 1, skin_AK47_STAT, skin_AK47_WEAR)
		else Weapons.AK47(skin_AK47, -1, 0, skin_AK47_WEAR)
		
		if (skin_AUG_STAT == 12) Weapons.AUG(skin_AUG, 12, 0, skin_AUG_WEAR)
		else if (skin_AUG_STAT > 0) Weapons.AUG(skin_AUG, 1, skin_AUG_STAT, skin_AUG_WEAR)
		else Weapons.AUG(skin_AUG, -1, 0, skin_AUG_WEAR)
		
		if (skin_AWP_STAT == 12) Weapons.AWP(skin_AWP, 12, 0, skin_AWP_WEAR)
		else if (skin_AWP_STAT > 0) Weapons.AWP(skin_AWP, 1, skin_AWP_STAT, skin_AWP_WEAR)
		else Weapons.AWP(skin_AWP, -1, 0, skin_AWP_WEAR)
		
		if (skin_CZ75A_STAT == 12) Weapons.CZ75A(skin_CZ75A, 12, 0, skin_CZ75A_WEAR)
		else if (skin_CZ75A_STAT > 0) Weapons.CZ75A(skin_CZ75A, 1, skin_CZ75A_STAT, skin_CZ75A_WEAR)
		else Weapons.CZ75A(skin_CZ75A, -1, 0, skin_CZ75A_WEAR)
		
		if (skin_DESERT_EAGLE_STAT == 12) Weapons.DESERT_EAGLE(skin_DESERT_EAGLE, 12, 0, skin_DESERT_EAGLE_WEAR)
		else if (skin_DESERT_EAGLE_STAT > 0) Weapons.DESERT_EAGLE(skin_DESERT_EAGLE, 1, skin_DESERT_EAGLE_STAT, skin_DESERT_EAGLE_WEAR)
		else Weapons.DESERT_EAGLE(skin_DESERT_EAGLE, -1, 0, skin_DESERT_EAGLE_WEAR)
		
		if (skin_FAMAS_STAT == 12) Weapons.FAMAS(skin_FAMAS, 12, 0, skin_FAMAS_WEAR)
		else if (skin_FAMAS_STAT > 0) Weapons.FAMAS(skin_FAMAS, 1, skin_FAMAS_STAT, skin_FAMAS_WEAR)
		else Weapons.FAMAS(skin_FAMAS, -1, 0, skin_FAMAS_WEAR)
		
		if (skin_FIVE_SEVEN_STAT == 12) Weapons.FIVE_SEVEN(skin_FIVE_SEVEN, 12, 0, skin_FIVE_SEVEN_WEAR)
		else if (skin_FIVE_SEVEN_STAT > 0) Weapons.FIVE_SEVEN(skin_FIVE_SEVEN, 1, skin_FIVE_SEVEN_STAT, skin_FIVE_SEVEN_WEAR)
		else Weapons.FIVE_SEVEN(skin_FIVE_SEVEN, -1, 0, skin_FIVE_SEVEN_WEAR)
		
		if (skin_G3SG1_STAT == 12) Weapons.G3SG1(skin_G3SG1, 12, 0, skin_G3SG1_WEAR)
		else if (skin_G3SG1_STAT > 0) Weapons.G3SG1(skin_G3SG1, 1, skin_G3SG1_STAT, skin_G3SG1_WEAR)
		else Weapons.G3SG1(skin_G3SG1, -1, 0, skin_G3SG1_WEAR)
		
		if (skin_GALIL_STAT == 12) Weapons.GALIL(skin_GALIL, 12, 0, skin_GALIL_WEAR)
		else if (skin_GALIL_STAT > 0) Weapons.GALIL(skin_GALIL, 1, skin_GALIL_STAT, skin_GALIL_WEAR)
		else Weapons.GALIL(skin_GALIL, -1, 0, skin_GALIL_WEAR)
		
		if (skin_GLOCK_STAT == 12) Weapons.GLOCK(skin_GLOCK, 12, 0, skin_GLOCK_WEAR)
		else if (skin_GLOCK_STAT > 0) Weapons.GLOCK(skin_GLOCK, 1, skin_GLOCK_STAT, skin_GLOCK_WEAR)
		else Weapons.GLOCK(skin_GLOCK, -1, 0, skin_GLOCK_WEAR)
		
		if (skin_M249_STAT == 12) Weapons.M249(skin_M249, 12, 0, skin_M249_WEAR)
		else if (skin_M249_STAT > 0) Weapons.M249(skin_M249, 1, skin_M249_STAT, skin_M249_WEAR)
		else Weapons.M249(skin_M249, -1, 0, skin_M249_WEAR)
		
		if (skin_M4A1_SILENCER_STAT == 12) Weapons.M4A1_SILENCER(skin_M4A1_SILENCER, 12, 0, skin_M4A1_SILENCER_WEAR)
		else if (skin_M4A1_SILENCER_STAT > 0) Weapons.M4A1_SILENCER(skin_M4A1_SILENCER, 1, skin_M4A1_SILENCER_STAT, skin_M4A1_SILENCER_WEAR)
		else Weapons.M4A1_SILENCER(skin_M4A1_SILENCER, -1, 0, skin_M4A1_SILENCER_WEAR)

		if (skin_M4A4_STAT == 12) Weapons.M4A4(skin_M4A4, 12, 0, skin_M4A4_WEAR)
		else if (skin_M4A4_STAT > 0) Weapons.M4A4(skin_M4A4, 1, skin_M4A4_STAT, skin_M4A4_WEAR)
		else Weapons.M4A4(skin_M4A4, -1, 0, skin_M4A4_WEAR)
		
		if (skin_MAC10_STAT == 12) Weapons.MAC10(skin_MAC10, 12, 0, skin_MAC10_WEAR)
		else if (skin_MAC10_STAT > 0) Weapons.MAC10(skin_MAC10, 1, skin_MAC10_STAT, skin_MAC10_WEAR)
		else Weapons.MAC10(skin_MAC10, -1, 0, skin_MAC10_WEAR)
		
		if (skin_MAG7_STAT == 12) Weapons.MAG7(skin_MAG7, 12, 0, skin_MAG7_WEAR)
		else if (skin_MAG7_STAT > 0) Weapons.MAG7(skin_MAG7, 1, skin_MAG7_STAT, skin_MAG7_WEAR)
		else Weapons.MAG7(skin_MAG7, -1, 0, skin_MAG7_WEAR)
		
		if (skin_MP7_STAT == 12) Weapons.MP7(skin_MP7, 12, 0, skin_MP7_WEAR)
		else if (skin_MP7_STAT > 0) Weapons.MP7(skin_MP7, 1, skin_MP7_STAT, skin_MP7_WEAR)
		else Weapons.MP7(skin_MP7, -1, 0, skin_MP7_WEAR)
		
		if (skin_MP9_STAT == 12) Weapons.MP9(skin_MP9, 12, 0, skin_MP9_WEAR)
		else if (skin_MP9_STAT > 0) Weapons.MP9(skin_MP9, 1, skin_MP9_STAT, skin_MP9_WEAR)
		else Weapons.MP9(skin_MP9, -1, 0, skin_MP9_WEAR)
		
		if (skin_NEGEV_STAT == 12) Weapons.NEGEV(skin_NEGEV, 12, 0, skin_NEGEV_WEAR)
		else if (skin_NEGEV_STAT > 0) Weapons.NEGEV(skin_NEGEV, 1, skin_NEGEV_STAT, skin_NEGEV_WEAR)
		else Weapons.NEGEV(skin_NEGEV, -1, 0, skin_NEGEV_WEAR)
		
		if (skin_NOVA_STAT == 12) Weapons.NOVA(skin_NOVA, 12, 0, skin_NOVA_WEAR)
		else if (skin_NOVA_STAT > 0) Weapons.NOVA(skin_NOVA, 1, skin_NOVA_STAT, skin_NOVA_WEAR)
		else Weapons.NOVA(skin_NOVA, -1, 0, skin_NOVA_WEAR)
		
		if (skin_P2000_STAT == 12) Weapons.P2000(skin_P2000, 12, 0, skin_P2000_WEAR)
		else if (skin_P2000_STAT > 0) Weapons.P2000(skin_P2000, 1, skin_P2000_STAT, skin_P2000_WEAR)
		else Weapons.P2000(skin_P2000, -1, 0, skin_P2000_WEAR)
		
		if (skin_P250_STAT == 12) Weapons.P250(skin_P250, 12, 0, skin_P250_WEAR)
		else if (skin_P250_STAT > 0) Weapons.P250(skin_P250, 1, skin_P250_STAT, skin_P250_WEAR)
		else Weapons.P250(skin_P250, -1, 0, skin_P250_WEAR)
		
		if (skin_P90_STAT == 12) Weapons.P90(skin_P90, 12, 0, skin_P90_WEAR)
		else if (skin_P90_STAT > 0) Weapons.P90(skin_P90, 1, skin_P90_STAT, skin_P90_WEAR)
		else Weapons.P90(skin_P90, -1, 0, skin_P90_WEAR)
		
		if (skin_PP_BIZON_STAT == 12) Weapons.PP_BIZON(skin_PP_BIZON, 12, 0, skin_PP_BIZON_WEAR)
		else if (skin_PP_BIZON_STAT > 0) Weapons.PP_BIZON(skin_PP_BIZON, 1, skin_PP_BIZON_STAT, skin_PP_BIZON_WEAR)
		else Weapons.PP_BIZON(skin_PP_BIZON, -1, 0, skin_PP_BIZON_WEAR)
		
		if (skin_R8_REVOLVER_STAT == 12) Weapons.R8_REVOLVER(skin_R8_REVOLVER, 12, 0, skin_R8_REVOLVER_WEAR)
		else if (skin_R8_REVOLVER_STAT > 0) Weapons.R8_REVOLVER(skin_R8_REVOLVER, 1, skin_R8_REVOLVER_STAT, skin_R8_REVOLVER_WEAR)
		else Weapons.R8_REVOLVER(skin_R8_REVOLVER, -1, 0, skin_R8_REVOLVER_WEAR)
		
		if (skin_SAWED_OFF_STAT == 12) Weapons.SAWED_OFF(skin_SAWED_OFF, 12, 0, skin_SAWED_OFF_WEAR)
		else if (skin_SAWED_OFF_STAT > 0) Weapons.SAWED_OFF(skin_SAWED_OFF, 1, skin_SAWED_OFF_STAT, skin_SAWED_OFF_WEAR)
		else Weapons.SAWED_OFF(skin_SAWED_OFF, -1, 0, skin_SAWED_OFF_WEAR)
		
		if (skin_SCAR20_STAT == 12) Weapons.SCAR20(skin_SCAR20, 12, 0, skin_SCAR20_WEAR)
		else if (skin_SCAR20_STAT > 0) Weapons.SCAR20(skin_SCAR20, 1, skin_SCAR20_STAT, skin_SCAR20_WEAR)
		else Weapons.SCAR20(skin_SCAR20, -1, 0, skin_SCAR20_WEAR)
		
		if (skin_SSG08_STAT == 12) Weapons.SSG08(skin_SSG08, 12, 0, skin_SSG08_WEAR)
		else if (skin_SSG08_STAT > 0) Weapons.SSG08(skin_SSG08, 1, skin_SSG08_STAT, skin_SSG08_WEAR)
		else Weapons.SSG08(skin_SSG08, -1, 0, skin_SSG08_WEAR)
		
		if (skin_SG556_STAT == 12) Weapons.SG556(skin_SG556, 12, 0, skin_SG556_WEAR)
		else if (skin_SG556_STAT > 0) Weapons.SG556(skin_SG556, 1, skin_SG556_STAT, skin_SG556_WEAR)
		else Weapons.SG556(skin_SG556, -1, 0, skin_SG556_WEAR)
		
		if (skin_TEC9_STAT == 12) Weapons.TEC9(skin_TEC9, 12, 0, skin_TEC9_WEAR)
		else if (skin_TEC9_STAT > 0) Weapons.TEC9(skin_TEC9, 1, skin_TEC9_STAT, skin_TEC9_WEAR)
		else Weapons.TEC9(skin_TEC9, -1, 0, skin_TEC9_WEAR)
		
		if (skin_UMP45_STAT == 12) Weapons.UMP45(skin_UMP45, 12, 0, skin_UMP45_WEAR)
		else if (skin_UMP45_STAT > 0) Weapons.UMP45(skin_UMP45, 1, skin_UMP45_STAT, skin_UMP45_WEAR)
		else Weapons.UMP45(skin_UMP45, -1, 0, skin_UMP45_WEAR)
		
		if (skin_USP_SILENCER_STAT == 12) Weapons.USP_SILENCER(skin_USP_SILENCER, 12, 0, skin_USP_SILENCER_WEAR)
		else if (skin_USP_SILENCER_STAT > 0) Weapons.USP_SILENCER(skin_USP_SILENCER, 1, skin_USP_SILENCER_STAT, skin_USP_SILENCER_WEAR)
		else Weapons.USP_SILENCER(skin_USP_SILENCER, -1, 0, skin_USP_SILENCER_WEAR)
		
		if (skin_XM1014_STAT == 12) Weapons.XM1014(skin_XM1014, 12, 0, skin_XM1014_WEAR)
		else if (skin_XM1014_STAT > 0) Weapons.XM1014(skin_XM1014, 1, skin_XM1014_STAT, skin_XM1014_WEAR)
		else Weapons.XM1014(skin_XM1014, -1, 0, skin_XM1014_WEAR)
	}


	fun skinwepindex() = every(1) {
		for (i in 1..3) try {
			if (ENABLE_SKINCHANGER) {
				val me: Player = clientDLL.uint(dwLocalPlayer)
				var currentWeaponIndex = csgoEXE.uint(me + hMyWeapons + ((i - 1) * 0x4))
				currentWeaponIndex = currentWeaponIndex and 0xFFF
				weaponAddress = clientDLL.int(dwEntityList + (currentWeaponIndex - 1) * 0x10)
				if (weaponAddress <= 0) continue
				val weaponID = csgoEXE.int(weaponAddress + iItemDefinitionIndex)
				csgoEXE[weaponAddress + iItemIDHigh] = -1
				weapon = Weapons[weaponID]
				skins()
			} else {Thread.Sleep(1000)}
		} catch (t: Throwable) {

		}
		val enginePointer = engineDLL.uint(dwClientState)
	if (keyPressed(67)) csgoEXE[enginePointer + 0x174] = -1
	}

}

val DEFAULT_STATTRAK = -1
val DEFAULT_SKIN_SEED = 1

val DEFAULT_WEAR = 0.0001F // lower = less wear, higher = more wear
val DEFAULT_QUALITY = 0

private fun skin(skinID: Int, skinSeed: Int, statTrak: Int, wear: Float, quality: Int){
    csgoEXE[SkinChangerPlugin.weaponAddress + nFallbackPaintKit] = skinID
	csgoEXE[SkinChangerPlugin.weaponAddress + nFallbackSeed] = skinSeed
	csgoEXE[SkinChangerPlugin.weaponAddress + nFallbackStatTrak] = statTrak
	csgoEXE[SkinChangerPlugin.weaponAddress + iEntityQuality] = quality
	csgoEXE[SkinChangerPlugin.weaponAddress + flFallbackWear] = wear
}
private operator fun Weapons.invoke(skinID: Int, skinSeed: Int = DEFAULT_SKIN_SEED,
	                                    statTrak: Int = DEFAULT_STATTRAK, wear: Float = DEFAULT_WEAR,
	                                    quality: Int = DEFAULT_QUALITY) {

	if (this ==  weapon) skin(skinID, skinSeed, statTrak, wear, quality)
}
