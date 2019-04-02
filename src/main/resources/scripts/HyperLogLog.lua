redis.call("PFADD", KEYS[1], ARGV[2]);
local result = redis.call("PFCOUNT", KEYS[1]);
redis.call("EXPIRE", KEYS[1], ARGV[1])
return result








