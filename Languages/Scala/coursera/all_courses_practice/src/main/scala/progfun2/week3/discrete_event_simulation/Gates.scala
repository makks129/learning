package progfun2.week3.discrete_event_simulation

abstract class Gates extends Simulation {

  def InverterDelay: Int

  def AndGateDelay: Int

  def OrGateDelay: Int

  class Wire {

    private var sigVal = false
    private var actions: List[Action] = List()

    def getSignal: Boolean = sigVal

    def setSignal(s: Boolean): Unit =
      if (s != sigVal) {
        sigVal = s
        actions foreach (_ ())
      }

    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }

  }

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction(): Unit = {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSig
      }
    }
    input addAction invertAction
  }

  def andGate(i1: Wire, i2: Wire, output: Wire): Unit = {
    def andAction(): Unit = {
      val i1Sig = i1.getSignal
      val i2Sig = i2.getSignal
      afterDelay(AndGateDelay) {
        output setSignal (i1Sig & i2Sig)
      }
    }
    i1 addAction andAction
    i2 addAction andAction
  }

  def orGate(i1: Wire, i2: Wire, output: Wire): Unit = {
    def orAction(): Unit = {
      val i1Sig = i1.getSignal
      val i2Sig = i2.getSignal
      afterDelay(OrGateDelay) {
        output setSignal (i1Sig | i2Sig)
      }
    }
    i1 addAction orAction
    i2 addAction orAction
  }

  def probe(name: String, wire: Wire): Unit = {
    def probeAction(): Unit = {
      println(s"$name $currentTime new-value = ${wire.getSignal}")
    }
    wire addAction probeAction
  }

}
