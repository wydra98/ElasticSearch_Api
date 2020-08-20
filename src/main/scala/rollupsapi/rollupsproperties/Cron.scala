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
  val seconds, minutes, hours, day, month, daily, monthly = Value
}

case class Cron() {

  def parseFromData(timeUnit: TimeUnit,value: Option[Int] = None): String = {

    var res = ""
    val x: Int =
      if(value.isEmpty) 1
      else value.get

    timeUnit match {
      case TimeUnit.seconds => res = s"*/${x} * * * * ?"; res     //every 1 second, 2 seconds, 4 seconds ... etc.
      case TimeUnit.minutes => res = s"0 0/${x} * 1/1 * ? *"; res //every 1 minute, 2 minutes, 4 minutes ... etc.
      case TimeUnit.hours => res = s"0 0 0/${x} 1/1 * ? *"; res   //every 1 hour, 2 hours, 4 hours ... etc.
      case TimeUnit.day => res = s"0 0 0 ? * ${x} *"; res         //every Monday or Tuesday or Friday ... etc.
      case TimeUnit.month => res = s"0 0 0 1 1/${x} ? *"; res     //every January or February or June ... etc.
      case TimeUnit.daily => res = s"0 0 0 * * ?"; res            //every day at midnight
      case TimeUnit.monthly => res = s"0 0 12 1 * ? ? *"; res     //every January or February or June ... etc.
      case _ => {res = "Not correct value"; res}

      res
    }
  }

}
