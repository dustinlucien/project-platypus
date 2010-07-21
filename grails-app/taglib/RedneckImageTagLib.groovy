

class RedneckImageTagLib {
  def rateableRedneckThumbnail = { attrs, body ->
    if (attrs?.first == 'true') {
      out << '''<div class="rate clear">'''
    } else {
      out << '''<div class="rate">'''
    }
    out <<  '''<img src="''' << body() << '''" /> '''
    out <<  '''<p><strong>"Redneck Jim"</strong><br />by Jim Johnson</p>'''
    out <<      '''<ul class="rating">'''
    out <<      '''  <li><a class="red" class="red" href="#"><span class="hidden">Rate 1</span></a></li>'''
    out <<      '''  <li><a class="red" href="#"><span class="hidden">Rate 2</span></a></li>'''
    out <<      '''  <li><a class="red" href="#"><span class="hidden">Rate 3</span></a></li>'''
    out <<      '''  <li><a href="#"><span class="hidden">Rate 4</span></a></li>'''
    out <<      '''  <li><a href="#"><span class="hidden">Rate 5</span></a></li>'''
    out <<      '''</ul>''' 
    out <<  '''</div>'''
  }
}