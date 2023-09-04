## NodeJs profiling playground

## Chrome DevTools

- Open: chrome://inspect
- Click: Open dedicated DevTools for Node
- Start: `node --inspect index.js` / `node --inspect-brk index.js`

### CPU
- Chrome DevTools -> Performance -> Record
- `curl http://localhost:3000/load-cpu`
- Chrome DevTools -> Performance -> Stop

### Memory
- Chrome DevTools -> Memory -> Take heap snapshot
- `curl http://localhost:3000/memory-leak`
- Chrome DevTools -> Memory -> Take heap snapshot
- Compare

**Shallow size:** This is the size of an object in memory, including the memory occupied by its properties and the size of any values stored in those properties.
**Retained size:** This is the total memory that would be freed if the object was garbage collected, including not only the shallow size of the object itself but also the shallow size of all objects referenced by the object, even if they are not directly referenced from other parts of the code.

#### Sources
- https://chromedevtools.github.io/devtools-protocol/v8/
- https://medium.com/@paul_irish/debugging-node-js-nightlies-with-chrome-devtools-7c4a1b95ae27
- https://nodejs.org/en/docs/guides/debugging-getting-started


## Clinic.js

[Clinic.js](https://www.clinicjs.org/)

---

## Sources

- [Debugging Node.js with Chrome DevTools](https://medium.com/@paul_irish/debugging-node-js-nightlies-with-chrome-devtools-7c4a1b95ae27)
- [Optimizing Node.js Performance: Detecting Memory Leaks and High CPU Usage](https://betterprogramming.pub/optimizing-node-js-performance-a-guide-to-detecting-memory-leaks-and-high-cpu-usage-cbdad77e7a98)
