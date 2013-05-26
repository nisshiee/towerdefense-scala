towerdefense-scala
========================================

[![Build Status](https://travis-ci.org/nisshiee/towerdefense-scala.png?branch=master)](https://travis-ci.org/nisshiee/towerdefense-scala)


how to use
----------------------------------------

### sbt dependency

```
libraryDependencies += "org.nisshiee" %% "towerdefense-scala" % "1.0.2"
```

### Scaladoc

http://nisshiee.github.io/towerdefense-scala/doc/1.0.2/

### Playerの実装

org.nisshiee.towerdefensescala.Playerを継承することで、
Sampleと同様にJavaからオブジェクトを生成して動作させることができるPlayerを実装できます。

例：

```scala
import org.nisshiee.towerdefensescala._

class MyPlayer(name: String) extends Player[Int](name) {

  def init: Int = 0

  def action: ((Snapshot, Int)) => ((Seq[Command], Int)) = {
    case (snapshot, turn) =>
      List(Command(Point(turn, 1), WeakTower)) -> (turn + 1)
  }
}
```

デフォルトコンストラクタの引数 `name: String` は、
Javaからの呼び出しに合わせるため、おそらく必須です。

Player abstract classは、型パラメータAを持っています。
Aは、actionの呼び出しを超えて引き継げる"状態"の型です。
この例ではIntを指定していますが、独自の型を指定できます。

initには、初回のaction呼び出し時に渡される"状態"を指定します。

actionが、AI部分です。
「ゲームの状態と、Playerの状態を受け取り、Commandと次のPlayerの状態を返す」関数を実装します。
