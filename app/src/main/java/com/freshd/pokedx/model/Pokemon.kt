package com.freshd.pokedx.model

class Pokemon {

    /*
    public int id { get; set; }
    public string num { get; set; }
    public string name { get; set; }
    public string img { get; set; }
    public List<string> type { get; set; }
    public string height { get; set; }
    public string weight { get; set; }
    public string candy { get; set; }
    public int candy_count { get; set; }
    public string egg { get; set; }
    public double spawn_chance { get; set; }
    public double avg_spawns { get; set; }
    public string spawn_time { get; set; }
    public List<double?> multipliers { get; set; }
    public List<string> weaknesses { get; set; }
    public List<NextEvolution> next_evolution { get; set; }
    public List<PrevEvolution> prev_evolution { get; set; }
     */

    var id: Int = 0
    var num: String = ""
    var name: String = ""
    var img: String = ""
    var type = emptyList<String>()
    var height: String = ""
    var weight: String = ""
    var candy: String = ""
    var candy_count = 0
    var egg = ""
    var spawn_chance = 0.0
    var avg_spawns = 0.0
    var spawn_time = ""
    var multipliers = emptyList<Double>()
    var weaknesses = emptyList<String>()
    var next_evolution = emptyList<Evolution>()
    var prev_evolution = emptyList<Evolution>()
}