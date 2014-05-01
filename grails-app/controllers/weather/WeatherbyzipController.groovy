package weather

class WeatherbyzipController {

	def index() {
	}

	def weatherbyzips(){


		def baseUrl = "http://weather.yahooapis.com/forecastrss"

		if (true){
			def zip = params.zipcode
			def url = baseUrl + "?p=" + zip
			def xml = url.toURL().text

			def rss = new XmlSlurper().parseText(xml)

			def rss_title = rss.channel.title
			def sunrise = rss.channel.astronomy.@sunrise
			def sunset = rss.channel.astronomy.@sunset
			def currentdate = rss.channel.item.condition.@date
			def temp = rss.channel.item.condition.@temp
			def text = rss.channel.item.condition.@text

			/*
			 render rss.channel.title
			 render "<br>Sunrise: ${rss.channel.astronomy.@sunrise}"
			 render "<br>Sunset: ${rss.channel.astronomy.@sunset}"
			 render "<br>Currently:"
			 render "&nbsp;" + rss.channel.item.condition.@date
			 render "&nbsp;" + rss.channel.item.condition.@temp
			 render "&nbsp;" + rss.channel.item.condition.@text
			 */
			render(view:'results',model: [rss_title: rss_title, sunrise: sunrise, sunset: sunset, currentdate: currentdate, temp: temp, text: text])
		}else{
			render "USAGE: weather zipcode"
		}
	}
}
