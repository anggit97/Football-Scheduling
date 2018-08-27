package com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team

import com.google.gson.annotations.SerializedName

data class TeamsItem(

	@SerializedName("intStadiumCapacity")
	var intStadiumCapacity: String? = null,

	@SerializedName("strTeamShort")
	var strTeamShort: String? = null,

	@SerializedName("intFormedYear")
	var intFormedYear: String? = null,

	@SerializedName("strSport")
	var strSport: String? = null,

	@SerializedName("strInstagram")
	var strInstagram: String? = null,

	@SerializedName("idTeam")
	var idTeam: String? = null,

	@SerializedName("strDescriptionEN")
	var strDescriptionEN: String? = null,

	@SerializedName("strTeamJersey")
	var strTeamJersey: String? = null,

	@SerializedName("strTeamFanart2")
	var strTeamFanart2: String? = null,

	@SerializedName("strTeamFanart3")
	var strTeamFanart3: String? = null,

	@SerializedName("strWebsite")
	var strWebsite: String? = null,

	@SerializedName("strTeamFanart4")
	var strTeamFanart4: String? = null,

	@SerializedName("strYoutube")
	var strYoutube: String? = null,

	@SerializedName("strStadiumDescription")
	var strStadiumDescription: String? = null,

	@SerializedName("strTeamFanart1")
	var strTeamFanart1: String? = null,

	@SerializedName("strLocked")
	var strLocked: String? = null,

	@SerializedName("intLoved")
	var intLoved: String? = null,

	@SerializedName("idLeague")
	var idLeague: String? = null,

	@SerializedName("idSoccerXML")
	var idSoccerXML: String? = null,

	@SerializedName("strAlternate")
	var strAlternate: String? = null,

	@SerializedName("strTeam")
	var strTeam: String? = null,

	@SerializedName("strTwitter")
	var strTwitter: String? = null,

	@SerializedName("strGender")
	var strGender: String? = null,

	@SerializedName("strTeamLogo")
	var strTeamLogo: String? = null,

	@SerializedName("strDivision")
	var strDivision: String? = null,

	@SerializedName("strStadium")
	var strStadium: String? = null,

	@SerializedName("strStadiumLocation")
	var strStadiumLocation: String? = null,

	@SerializedName("strFacebook")
	var strFacebook: String? = null,

	@SerializedName("strTeamBadge")
	var strTeamBadge: String? = null,

	@SerializedName("strCountry")
	var strCountry: String? = null,

	@SerializedName("strRSS")
	var strRSS: String? = null,

	@SerializedName("strTeamBanner")
	var strTeamBanner: String? = null,

	@SerializedName("strLeague")
	var strLeague: String? = null,

	@SerializedName("strManager")
	var strManager: String? = null,

	@SerializedName("strKeywords")
	var strKeywords: String? = null,

	@SerializedName("strStadiumThumb")
	var strStadiumThumb: String? = null
)