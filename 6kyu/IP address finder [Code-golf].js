/** The shortest code I could come up with. Still 12 chars pass the limit */

f=inp=>[...inp].map((v,i,a)=>a.reduce((s,c)=>s+c.charCodeAt(0),0)*(i+1)%256).splice(0,4);
