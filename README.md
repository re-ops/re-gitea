# Intro

Re-gitea is a collection of recipes that use Re-conf for setting up the ELK stack

[![Build Status](https://travis-ci.org/re-ops/re-gitea.png)](https://travis-ci.org/re-ops/re-gitea)

# Development

```bash
$ lein npm install
```

## VIM

```bash
$ lein repl
```

```clojure
=> (require 'figwheel.main.api)

=> (figwheel.main.api/start {:mode :serve} "dev")

=> (figwheel.main.api/cljs-repl "dev")
```

In the VIM session:

```bash
:Piggieback (figwheel.main.api/repl-env "dev")
```

# Copyright and license

Copyright [2018] [Ronen Narkis]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
