import progfun2.week3.discrete_event_simulation._

object sim extends Circuits with Parameters

import sim._

val in1, in2, sum, carry = new Wire

halfAdder(in1, in2, sum, carry)

probe("sum", sum)
probe("carry", carry)

in1 setSignal true
run()