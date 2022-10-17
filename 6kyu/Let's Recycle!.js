function recycle(array) {
    const bins = [[], [], [], []];
    array.forEach(trash => {
       segragate(bins, trash.type, trash.material);
       if(trash.secondMaterial) { segragate(bins, trash.type, trash.secondMaterial); }
    });
    return bins;
  }
  
  function segragate(bins, type, material) {
    switch(material) {
       case 'paper': bins[0].push(type)
                      break;
        case 'glass': bins[1].push(type)
                      break;
        case 'organic': bins[2].push(type)
                        break;
        case 'plastic': bins[3].push(type)
                        break;
        default: break;
    }
  }