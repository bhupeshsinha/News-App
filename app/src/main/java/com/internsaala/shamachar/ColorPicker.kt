package com.internsaala.shamachar

object ColorPicker{
    val colors = arrayOf("#e1bee7", "#ffcdd2","#f8bbd0", "#d1c4e9", "#c5cae9",
        "#bbdefb", "#b2dfdb", "#b2ebf2", "#f0f4c3", "#dcedc8",
        "#c8e6c9", "#ffe0b2", "#ffecb3", "#fff9c4", "#ffccbc",
        "#d7ccc8", "#cfd8dc", "#9e9e9e", "#ffd740")

    var colorIndex = 1

    fun getColor(): String{
        return colors[colorIndex++ % colors.size]
    }

}