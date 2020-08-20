package rollupsapi.rollupsproperties

import rollupsapi.rollupsproperties.TimeUnit.TimeUnit


/**
 * Generate a CRON expression is a string comprising 6 or 7 fields separated by white space.
 *
 * seconds    mandatory = yes. allowed values = { @code {1,2,3,4,5,6,10,20,30}   * / , -}
 * minutes    mandatory = yes. allowed values = { @code {1,2,3,4,5,6,10,20,30}   * / , -}
 * hours      mandatory = yes. allowed values = { @code {1,2,3,4,6,12}           * / , -}
 * day        mandatory = yes. allowed values = { @code {0,1,2,3,4,5,6}       * / , - ? L #}
 * month      mandatory = yes. allowed values = { @code {1,2,3,4,5,6,7,8,9,10,11,12}     * / , -}
 *
 * @return a CRON Formatted String.
 */

object TimeUnit extends Enumeration {
  type TimeUnit = Value
  val seconds, minutes, hours, month, day = Value
}

case class Cron() {

  def parseFromDataWithValue(timeUnit: TimeUnit, value: Int): String = {

    var dataString = ""

    timeUnit match {
      case TimeUnit.seconds => { dataString = s"*/${value} * * * * ?"; dataString}      //every 1 second, 2 seconds, 4 seconds ... etc.
      case TimeUnit.minutes => { dataString = s"0 0/${value} * 1/1 * ? *"; dataString}  //every 1 minute, 2 minutes, 4 minutes ... etc.
      case TimeUnit.hours => { dataString = s"0 0 0/${value} 1/1 * ? *"; dataString}    //every 1 hour, 2 hours, 4 hours ... etc.
      case TimeUnit.day => { dataString = s"0 0 0 ? * ${value} *"; dataString}          //every Monday or Tuesday or Friday ... etc.
      case TimeUnit.month => { dataString = s"0 0 0 1 1/${value} ? *"; dataString}      //every January or February or June ... etc.
      case _ => {dataString = "Not correct value"; dataString}

      dataString
    }
  }

}
