package com.tkpd.basketballapp.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NbaBasketballTeams(
    @Expose
    @SerializedName("teams")
    val teams: List<Team> = listOf()
)

data class Team(
    @Expose
    @SerializedName("idAPIfootball")
    val idAPIfootball: String = "",
    @Expose
    @SerializedName("idLeague")
    val idLeague: String = "",
    @Expose
    @SerializedName("idLeague2")
    val idLeague2: Any = Any(),
    @Expose
    @SerializedName("idLeague3")
    val idLeague3: Any = Any(),
    @Expose
    @SerializedName("idLeague4")
    val idLeague4: Any = Any(),
    @Expose
    @SerializedName("idLeague5")
    val idLeague5: Any = Any(),
    @Expose
    @SerializedName("idLeague6")
    val idLeague6: Any = Any(),
    @Expose
    @SerializedName("idLeague7")
    val idLeague7: Any = Any(),
    @Expose
    @SerializedName("idSoccerXML")
    val idSoccerXML: Any = Any(),
    @Expose
    @SerializedName("idTeam")
    val idTeam: String = "",
    @Expose
    @SerializedName("intFormedYear")
    val intFormedYear: String = "",
    @Expose
    @SerializedName("intLoved")
    val intLoved: String = "",
    @Expose
    @SerializedName("intStadiumCapacity")
    val intStadiumCapacity: String = "",
    @Expose
    @SerializedName("strAlternate")
    val strAlternate: String = "",
    @Expose
    @SerializedName("strCountry")
    val strCountry: String = "",
    @Expose
    @SerializedName("strDescriptionCN")
    val strDescriptionCN: Any = Any(),
    @Expose
    @SerializedName("strDescriptionDE")
    val strDescriptionDE: String = "",
    @Expose
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String = "",
    @Expose
    @SerializedName("strDescriptionES")
    val strDescriptionES: Any = Any(),
    @Expose
    @SerializedName("strDescriptionFR")
    val strDescriptionFR: String = "",
    @Expose
    @SerializedName("strDescriptionHU")
    val strDescriptionHU: Any = Any(),
    @Expose
    @SerializedName("strDescriptionIL")
    val strDescriptionIL: Any = Any(),
    @Expose
    @SerializedName("strDescriptionIT")
    val strDescriptionIT: Any = Any(),
    @Expose
    @SerializedName("strDescriptionJP")
    val strDescriptionJP: Any = Any(),
    @Expose
    @SerializedName("strDescriptionNL")
    val strDescriptionNL: String = "",
    @Expose
    @SerializedName("strDescriptionNO")
    val strDescriptionNO: String = "",
    @Expose
    @SerializedName("strDescriptionPL")
    val strDescriptionPL: Any = Any(),
    @Expose
    @SerializedName("strDescriptionPT")
    val strDescriptionPT: String = "",
    @Expose
    @SerializedName("strDescriptionRU")
    val strDescriptionRU: String = "",
    @Expose
    @SerializedName("strDescriptionSE")
    val strDescriptionSE: Any = Any(),
    @Expose
    @SerializedName("strDivision")
    val strDivision: Any = Any(),
    @Expose
    @SerializedName("strFacebook")
    val strFacebook: String = "",
    @Expose
    @SerializedName("strGender")
    val strGender: String = "",
    @Expose
    @SerializedName("strInstagram")
    val strInstagram: String = "",
    @Expose
    @SerializedName("strKeywords")
    val strKeywords: String = "",
    @Expose
    @SerializedName("strLeague")
    val strLeague: String = "",
    @Expose
    @SerializedName("strLeague2")
    val strLeague2: Any = Any(),
    @Expose
    @SerializedName("strLeague3")
    val strLeague3: Any = Any(),
    @Expose
    @SerializedName("strLeague4")
    val strLeague4: Any = Any(),
    @Expose
    @SerializedName("strLeague5")
    val strLeague5: Any = Any(),
    @Expose
    @SerializedName("strLeague6")
    val strLeague6: Any = Any(),
    @Expose
    @SerializedName("strLeague7")
    val strLeague7: Any = Any(),
    @Expose
    @SerializedName("strLocked")
    val strLocked: String = "",
    @Expose
    @SerializedName("strManager")
    val strManager: String = "",
    @Expose
    @SerializedName("strRSS")
    val strRSS: String = "",
    @Expose
    @SerializedName("strSport")
    val strSport: String = "",
    @Expose
    @SerializedName("strStadium")
    val strStadium: String = "",
    @Expose
    @SerializedName("strStadiumDescription")
    val strStadiumDescription: String = "",
    @Expose
    @SerializedName("strStadiumLocation")
    val strStadiumLocation: String = "",
    @Expose
    @SerializedName("strStadiumThumb")
    val strStadiumThumb: String = "",
    @Expose
    @SerializedName("strTeam")
    val strTeam: String = "",
    @Expose
    @SerializedName("strTeamBadge")
    val strTeamBadge: String = "",
    @Expose
    @SerializedName("strTeamBanner")
    val strTeamBanner: String = "",
    @Expose
    @SerializedName("strTeamFanart1")
    val strTeamFanart1: String = "",
    @Expose
    @SerializedName("strTeamFanart2")
    val strTeamFanart2: String = "",
    @Expose
    @SerializedName("strTeamFanart3")
    val strTeamFanart3: String = "",
    @Expose
    @SerializedName("strTeamFanart4")
    val strTeamFanart4: String = "",
    @Expose
    @SerializedName("strTeamJersey")
    val strTeamJersey: String = "",
    @Expose
    @SerializedName("strTeamLogo")
    val strTeamLogo: String = "",
    @Expose
    @SerializedName("strTeamShort")
    val strTeamShort: String = "",
    @Expose
    @SerializedName("strTwitter")
    val strTwitter: String = "",
    @Expose
    @SerializedName("strWebsite")
    val strWebsite: String = "",
    @Expose
    @SerializedName("strYoutube")
    val strYoutube: String = ""
)
