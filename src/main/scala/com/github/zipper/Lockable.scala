package com.github.zipper

import java.util.concurrent.locks.ReentrantLock

trait Lockable {
  private lazy val lock = new ReentrantLock()
  protected def lockingOperation(operation: => Any) = {
    lock.lock()
    try operation
    finally lock.unlock()
  }
}
